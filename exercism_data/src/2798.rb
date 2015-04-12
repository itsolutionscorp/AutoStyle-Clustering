class Hamming
  def compute(a_str, b_str)
    distance = 0
    a_ary = a_str.split('')
    b_ary = b_str.split('')

    a_ary.zip(b_ary).each do |a, b|
      next if a.nil? || b.nil?
      distance += 1 if a != b
    end

    distance
  end
end
