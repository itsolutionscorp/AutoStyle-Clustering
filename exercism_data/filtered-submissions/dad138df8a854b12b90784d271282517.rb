class Hamming
  def compute(one, two)
    difference = 0

    one.length.times do |position|
      difference += 1 if one[position] != two[position]
    end

    difference
  end
end
