module Hamming
  def self.compute(s, t)
    differences(s,t).count(false)
  end

  def self.differences(s,t)
    Conversions::Strand(s) ^ Conversions::Strand(t)
  end

  module Conversions
    def self.Strand(input)
      input.is_a?(Strand) ? input : Strand.parse(input)
    end
  end

  class Strand
    attr_reader :segments

    def self.parse(str)
      new(str.split(''))
    end

    def initialize(segments = [])
      @segments = segments
    end

    def length
      @segments.length
    end

    def crop(length)
      self.class.new(segments[0,length])
    end

    def ^(other)
      other = Conversions::Strand(other)

      if length != other.length
        minimum = [length, other.length].min
        crop(minimum) ^ other.crop(minimum)
      else
        compare_segments(other)
      end
    end

    def compare_segments(other)
      segments.zip(other.segments).collect do |seg1, seg2|
        seg1 == seg2
      end
    end
  end
end
