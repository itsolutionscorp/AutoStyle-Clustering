class Hamming
  def compute(source, dest)
    thing = 0
    source.chars.each_with_index do |char, numb|
      char == dest.chars[numb] || dest.chars[numb].nil? ? false : thing += 1
    end
    thing
  end
end
