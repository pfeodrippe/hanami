(ns aerial.hanami.templates
  #?(:cljs
     (:require [aerial.hanami.utils :as hu :refer [format]]))
  )



(def default-tooltip
  [{:field :X :type :XTYPE}
   {:field :Y :type :YTYPE}])

(def default-mark-props
  {:field :MPFIELD :type :MPTYPE})

(def default-color
  {:field :CFIELD :type :CTYPE :scale :CSCALE})

(def default-title
  {:text :TTEXT :offset :TOFFSET})

(def default-row
  {:field :ROW :type :ROWTYPE})

(def default-col
  {:field :COLUMN :type :COLTYPE})

(def data-options
  {:values :VALDATA, :url :UDATA, :name :NDATA})


(def interval-scales
  {:INAME
   {:type "interval",
    :bind "scales", ; <-- This gives zoom and pan
    ;:on "[mousedown, window:mouseup] > window:mousemove!",
    ;:on "[mousedown[event.shiftKey], mouseup] > mousemove!",
    :translate
    "[mousedown[event.shiftKey], window:mouseup] > window:mousemove!"
    :encodings :ENCODINGS,
    :zoom "wheel!",
    :resolve :IRESOLVE}})

(def interval-brush-mdwn
  {:MDWM-NAME
   {:type "interval"
    :on "[mousedown[!event.shiftKey], window:mouseup] > window:mousemove!"
    :translate "[mousedown[!event.shiftKey], window:mouseup] > window:mousemove"
    :resolve :IRESOLVE
    :mark :MDWN-MARK}})

(def interval-brush-smdwn
  {:SMDWM-NAME
   {:type "interval"
    :on "[mousedown[event.shiftKey], mouseup] > mousemove"
    :translate "[mousedown[event.shiftKey], mouseup] > mousemove"
    :resolve :IRESOLVE
    :mark :SMDWN-MARK}})


(def xy-encoding
  {:x {:field :X
       :type :XTYPE
       :timeUnit :XUNIT
       :axis :XAXIS
       :scale :XSCALE
       :aggregate :XAGG}
   :y {:field :Y
       :type :YTYPE
       :timeUnit :YUNIT
       :axis :YAXIS
       :scale :YSCALE
       :aggregate :YAGG}
   :opacity :OPACITY
   :row :ROWDEF
   :column :COLDEF
   :color :COLOR
   :size :SIZE
   :shape :SHAPE
   :tooltip :TOOLTIP})




(def xrule-layer
  {:mark "rule"
   :transform :TRANSFORM
   :encoding {:x {:field :X
                  :type :XTYPE
                  :aggregate :AGG}
              :size {:value 2}
              :color {:value :XRL-COLOR}}})

(def yrule-layer
  {:mark "rule"
   :transform :TRANSFORM
   :encoding {:y {:field :Y
                  :type :YTYPE
                  :aggregate :AGG}
              :size {:value 2}
              :color {:value :YRL-COLOR}}})


(def bar-layer
  {:mark "bar"
   :selection :SELECTION
   :transform :TRANSFORM
   :encoding :ENCODING})

(def line-layer
  {:mark {:type "line", :point :POINT}
   :selection :SELECTION
   :transform :TRANSFORM
   :encoding :ENCODING})

(def point-layer
  {:mark "circle"
   :selection :SELECTION
   :transform :TRANSFORM
   :encoding :ENCODING})

(def area-layer
  {:mark "area"
   :selection :SELECTION
   :transform :TRANSFORM
   :encoding :ENCODING})

(def gen-encode-layer
  {:height :HEIGHT, :width :WIDTH
   :mark :MARK
   :transform :TRANSFORM
   :selection :SELECTION
   :encoding :ENCODING})

(def view-base
  {:usermeta :USERDATA
   :title :TITLE
   :height :HEIGHT
   :width :WIDTH
   :background :BACKGROUND
   :selection :SELECTION
   :data data-options
   :transform :TRANSFORM
   :encoding :ENCODING})

(def mark-base
  {:type :MARK, :point :POINT,
   :size :MSIZE, :color :MCOLOR,
   :filled :MFILLED})


(def empty-chart
  {:usermeta :USERDATA})

(def bar-chart
  (assoc view-base
         :mark (merge mark-base {:type "bar"})))

(def line-chart
  (assoc view-base
         :mark (merge mark-base {:type "line"})))

(def point-chart
  (assoc view-base
         :mark (merge mark-base {:type "circle"})))

(def area-chart
  (assoc view-base
         :mark (merge mark-base {:type "area"})))


(def layer-chart
  {:usermeta :USERDATA
   :title  :TITLE
   :height :HEIGHT
   :width :WIDTH
   :background :BACKGROUND
   :layer :LAYER
   :resolve :RESOLVE
   :data data-options
   :config {:bar {:binSpacing 1
                  :discreteBandSize 5
                  :continuousBandSize 5}
            #_:view #_{:stroke "transparent"},
            #_:axis #_{:domainWidth 1}}})


(def hconcat-chart
  {:usermeta :USERDATA
   :title  :TITLE
   :height :HEIGHT
   :width :WIDTH
   :background :BACKGROUND
   :hconcat :HCONCAT
   :resolve :RESOLVE
   :data data-options
   :config {:bar {:binSpacing 1
                  :discreteBandSize 5
                  :continuousBandSize 5}}})

(def vconcat-chart
  {:usermeta :USERDATA
   :title  :TITLE
   :height :HEIGHT
   :width :WIDTH
   :background :BACKGROUND
   :vconcat :VCONCAT
   :resolve :RESOLVE
   :data data-options
   :config {:bar {:binSpacing 1
                  :discreteBandSize 5
                  :continuousBandSize 5}}})

(def overview-detail
  {:usermeta :USERDATA
   :data data-options
   :vconcat
   [{:mark :MARK,
     :height :HEIGHT, :width :WIDTH,
     :encoding :ENCODING}
    {:mark :MARK,
     :height :DHEIGHT, :width :WIDTH,
     :selection {:brush {:type "interval", :encodings ["x"]}},
     :encoding :ENCODING}]})


(def grouped-bar-chart
  {:usermeta :USERDATA
   :title  :TITLE
   :height :HEIGHT
   :width  :WIDTH
   :background :BACKGROUND
   :selection :SELECTION
   :data data-options

   :mark "bar"
   :encoding :ENCODING

   :config {:bar {:binSpacing 0
                  :discreteBandSize 1
                  :continuousBandSize 1}
            :view {:stroke "transparent"},
            :axis {:domainWidth 1}}})




;;; ============= Vega Templates ==========================================;;;


(def contour-plot
  {:usermeta :USERDATA
   :$schema "https://vega.github.io/schema/vega/v4.json",
   :autosize "pad",
   :legends [{:fill "color", :type "gradient"}],
   :config {:range {:heatmap {:scheme "greenblue"}}},
   :height :HEIGHT, :width :WIDTH,
   :padding 5,
   :axes [{:scale "x",
           :grid true,
           :domain false,
           :orient "bottom",
           :title :XTITLE}
          {:scale "y",
           :grid true,
           :domain false,
           :orient "left",
           :title :YTITLE}],
   :scales [{:name "x",
             :type "linear",
             :round true,
             :nice true,
             :zero false,
             :domain {:data "source", :field :X},
             :range "width"}
            {:name "y",
             :type "linear",
             :round true,
             :nice true,
             :zero false,
             :domain {:data "source", :field :Y},
             :range "height"}
            {:name "color",
             :type "sequential",
             :zero true,
             :domain {:data "contours", :field "value"},
             :range "heatmap"}],
   :marks [{:type "path",
            :from {:data "contours"},
            :encode {:enter
                     {:stroke {:value "#888"},
                      :strokeWidth {:value 1},
                      :fill {:scale "color", :field "value"},
                      :fillOpacity {:value 0.35}}},
            :transform [{:type "geopath", :field "datum"}]}
           {:name "marks",
            :type "symbol",
            :from {:data "source"},
            :encode {:update
                     {:x {:scale "x", :field :X},
                      :y {:scale "y", :field :Y},
                      :size {:value 4},
                      :fill [{:test "points", :value "black"}
                             {:value "transparent"}]}}}],
   :signals [{:name "count",
              :value 10,
              :bind {:input "select", :options [1 5 10 20]}}
             {:name "points",
              :value true,
              :bind {:input "checkbox"}}],
   :data [{:name "source",
           :values :DATA :url :UDATA
           :transform [{:type "filter",
                        :expr :XFORM-EXPR}]}
          {:name "contours",
           :source "source",
           :transform [{:type "contour",
                        :x {:expr #(format "scale('x', datum.%s)" (% :X))},
                        :y {:expr #(format "scale('y', datum.%s)" (% :Y))},
                        :size [{:signal "width"} {:signal "height"}],
                        :count {:signal "count"}}]}]})


(def tree-layout
  {:usermeta :USERDATA
   :$schema "https://vega.github.io/schema/vega/v4.json",
   :width :WIDTH,
   :height :HEIGHT,
   :padding 5,
   :signals :SIGNALS
   :data [{:name "tree",
           :values :VALDATA :url :UDATA
           :transform [{:type "stratify", :key :KEY, :parentKey :PARENTKEY}
                       {:type "tree",
                        :method :LAYOUT
                        :size :TREESIZE,
                        :as :TREEAS}]}
          {:name "links",
           :source "tree",
           :transform
           [{:type "treelinks"}
            {:type "linkpath",
             :orient :ORIENT
             :shape :LINKSHAPE}]}],
   :scales [{:name "color",
             :type "sequential",
             :range {:scheme :CSCHEME},
             :domain {:data "tree", :field :CFIELD},
             :zero true}],
   :marks [{:type "path",
            :from {:data "links"},
            :encode {:update {:path {:field "path"},
                              :stroke {:value "#ccc"}}}}
           {:type "symbol",
            :from {:data "tree"},
            :encode {:enter {:size {:value 100}, :stroke {:value "#fff"}},
                     :update {:x {:field :X},
                              :y {:field :Y},
                              :fill {:scale "color", :field :CFIELD}}}}
           {:type "text",
            :from {:data "tree"},
            :encode {:enter {:text {:field :NAME},
                             :fontSize {:value :FONTSIZE},
                             :baseline {:value :BASELINE}},
                     :update {:x {:field :X},
                              :y {:field :Y},
                              :dx
                              {:signal "datum.children ? -7 : 7"},
                              :align
                              {:signal "datum.children ? 'right' : 'left'"},
                              :opacity :OPACITY}}}]})
