class Hamming 
  def compute(let1, let2)
    sum = 0
    all_letters = let1.chars.zip(let2.chars)
    all_letters.each do |pair|
      if pair[0] != pair[1]
        sum += 1
      end
    end
    sum
  end
end
