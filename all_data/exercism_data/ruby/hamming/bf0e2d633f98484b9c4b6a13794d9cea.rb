class Hamming
  def self.compute(first, second)
    return 0 if first == second

    first, second = normalize_length(first, second)
    first, second = [first,second].map {|s| extract_chars s }

    first.zip(second).map do |f, s|
      f != s ? 1 : 0
    end.inject(&:+)
  end

  def self.normalize_length(first, second)
    l = [first.length, second.length].min
    return first[0...l], second[0...l]
  end

  def self.extract_chars(string)
    string.split('')
  end
end
