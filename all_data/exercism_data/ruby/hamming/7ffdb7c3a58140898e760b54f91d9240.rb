class Hamming
  attr_reader :a, :b

  class << self

  def compute(a, b)
    @non_matches = 0
    c = a.length - 1
    d = b.length - 1
    if a.length > b.length
      a = a[0..d]
    elsif b.length > a.length
      b = b[0..c]
    end
    strands_to_letters(a, b)
    check_for_matches
    @non_matches
  end

  def check_for_matches
    @new_a.each_with_index do |letter, pos|
      if @new_b[pos] != letter
        add_to_non_matches
      end
    end
  end

  def strands_to_letters(a, b)
    @new_a = a.split("")
    @new_b = b.split("")
  end

  def add_to_non_matches
    @non_matches += 1
  end

  end
end
