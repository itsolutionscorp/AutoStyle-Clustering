# class DNA
class DNA
  attr_accessor :str
  def initialize(str)
    @str = str
    validate
  end

  def count(s)
    raise ArgumentError.new unless %w{G A C T}.include? s
    nucleotide_counts[s] || 0
  end

  def nucleotide_counts
    hash = {}
    %w{G A C T}.each { |e| hash[e] = 0 }
    str.each_char { |chr| hash[chr] += 1 }
    hash
  end

  def validate
    raise ArgumentError.new if (str.split(//).uniq - %w{G A C T}) != []
  end
end
