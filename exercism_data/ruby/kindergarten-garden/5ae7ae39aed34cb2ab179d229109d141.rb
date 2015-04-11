require 'ostruct'

class Garden < OpenStruct
  DEFAULT_CHILDREN = %w(
    Alice Bob Charlie David
    Eve Fred Ginny Harriet
    Ileana Joseph Kincaid Larry
  )
  PLANTS = {
    G: :grass,
    C: :clover,
    R: :radishes,
    V: :violets
  }

  def initialize(diagram, children = nil)
    super()
    @plantings = parse_diagram(diagram)
    @children = (children || DEFAULT_CHILDREN).sort

    setup
  end

  private

  def parse_diagram(diagram)
    diagram.split("\n").map do |line|
      line.chars.each_slice(2).to_a
    end
  end

  def child_name(idx)
    @children[idx].downcase
  end

  def each_plants_with_index
    a, b = @plantings
    a.zip(b).each.with_index { |c, idx| yield c.flatten, idx }
  end

  def attributes_to_syms(plants)
    plants.map { |attribute| PLANTS[attribute.to_sym] }
  end

  def setup
    each_plants_with_index do |plants, idx|
      self[child_name(idx)] = attributes_to_syms(plants)
    end
  end
end
