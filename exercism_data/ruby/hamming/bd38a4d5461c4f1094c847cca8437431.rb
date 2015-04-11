class Hamming

  attr_reader :difference_count

  def self.compute(s, t)
    new.compute(s, t)
  end

  def initialize
    @difference_count = 0
  end

  def compute(s, t)
    string_one = split_string(s)
    string_two = split_string(t)

    if string_one.length > string_two.length
      string_one = string_one[0..string_two.length - 1]
    elsif string_two.length > string_one.length
      string_two = string_two[0..string_one.length - 1]
    end

    string_one.each_with_index { |char, i| @difference_count += 1 unless char == string_two[i] }

    @difference_count
  end

  private

  def split_string(string)
    string.split(//)
  end

end
