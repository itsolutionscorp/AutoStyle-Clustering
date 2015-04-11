class Series
  attr_reader :seq

  def initialize str
    @seq = str.chars.map(&:to_i)
  end

  def slices times
    raise ArgumentError if times > seq.size

    [].tap do |res| 
      seq.each_cons(times) { |a| res << a } 
    end
  end

end
