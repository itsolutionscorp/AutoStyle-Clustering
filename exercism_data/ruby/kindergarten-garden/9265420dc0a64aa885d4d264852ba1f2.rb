require 'pry'
class Garden

  PLANTS = {
            "G" => :grass,
            "C" => :clover,
            "R" => :radishes,
            "V" => :violets
  }

  CHILDREN = [:alice,
              :bob,
              :charlie,
              :david,
              :eve,
              :fred,
              :ginny,
              :harriet,
              :ileana,
              :joseph,
              :kincaid,
              :larry
  ]

  CUPS_PER_ROW = 2

  def initialize(cups, children=CHILDREN)
    @cups = cups
    @children = generate_children_hash(children)
    process_diagram()
    define_methods_for_children()
  end

  private

  def generate_children_hash(children)
    children.sort.each_with_object({}) do |child, hash|
      hash[child.downcase.to_sym] = []
    end
  end

  def define_methods_for_children
    @children.keys.each do |child|
      self.class.send(:define_method, child) do
        @children[__method__]
      end
    end
  end

  def process_diagram
    @cups.lines.each do |line|
      line.chomp.chars.each_slice(CUPS_PER_ROW).with_index do |slice, i|
        slice.each{|plant| @children[@children.keys[i]] << PLANTS[plant]}
      end
    end
  end

end
