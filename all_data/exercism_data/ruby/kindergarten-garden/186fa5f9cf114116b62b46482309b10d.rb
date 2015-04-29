class Garden
  attr_reader :rows

  def initialize(plants, children = default_children)
    @rows = parse_plants(plants).map do |sequence|
      sequence.map do |char|
        plant_symbol_for_char(char)
      end
    end

    children.sort.each_with_index do |child, index|
      self.singleton_class.instance_eval do
        define_method child.downcase do
          rows[index]
        end
      end
    end
  end

  def parse_plants(plants)
    plants.split("\n").map(&:chars).
      transpose.each_slice(2).
      map { |a, b| a.zip(b).flatten }
  end

  def plant_symbol_for_char(plant_char)
    plants_map[plant_char]
  end

  def plants_map
    {
      "C" => :clover,
      "G" => :grass,
      "R" => :radishes,
      "V" => :violets
    }
  end

  def default_children
    %w(
      Alice
      Bob
      Charlie
      David
      Eve
      Fred
      Ginny
      Harriet
      Ileana
      Joseph
      Kincaid
      Larry
    )
  end
end
