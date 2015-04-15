class DNA
  attr_reader :counter, :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def count(code)
    return 0 if code == 'U'
    nucleotide_counts[code]
  end

  def nucleotide_counts
    @counter ||= begin
      counter = build_counter
      sequence.chars.each {|code| counter[code] += 1 }
      counter
    end
  end

  private
  def build_counter
    #A, G, C and U
    Hash.new{ raise ArgumentError }.tap do |counter|
      counter["A"] = 0
      counter["T"] = 0
      counter["C"] = 0
      counter["G"] = 0
    end
  end
end
