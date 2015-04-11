class Hamming
  def self.compute(one , two)
    counter = 0
    one.split('').each_with_index do |char,index|
      return counter if index >= two.length
      counter += 1 unless one[index] == two[index]
    end
    counter
  end
end
