require 'ostruct'

class Garden < OpenStruct
  LINE_LENGTH = 24
  DEFAULT_CHILDREN = %w(
    Alice Bob Charlie David
    Eve Fred Ginny Harriet
    Ileana Joseph Kincaid Larry
  )

  def initialize(diagram, children = nil)
    super()
    @diagram = diagram
    @children = (children || DEFAULT_CHILDREN).sort

    setup
  end

  private

  def plant_name(attribute)
    {
      G: :grass,
      C: :clover,
      R: :radishes,
      V: :violets
    }[attribute.to_sym]
  end

  def padded_diagram
    @diagram.split("\n").map do |line|
      padded = line.chars + Array.new(LINE_LENGTH - line.length)
      padded.each_slice(2).to_a
    end
  end

  def child_name(idx)
    @children[idx].nil? ? nil : @children[idx].downcase
  end

  def each_plants_with_index
    a, b = padded_diagram
    a.zip(b).each.with_index { |c, idx| yield c.flatten, idx }
  end

  def attributes_to_syms(plants)
    plants.map { |attribute| plant_name(attribute) unless attribute.nil? }
  end

  def setup
    each_plants_with_index do |plants, idx|
      self[child_name(idx)] = attributes_to_syms(plants) unless child_name(idx).nil?
    end
  end
end
