class Hamming
  def compute(a,b)
    a,b = [a.chars,b.chars]
    (longest,shortest) = [a.size,b.size].max == a.size ? [a,b] : [b,a]
    hamming_count = 0
    shortest.to_ary.each_with_index do |ch,i|
      hamming_count += 1 if ch != longest[i]
    end
    hamming_count
  end
end
