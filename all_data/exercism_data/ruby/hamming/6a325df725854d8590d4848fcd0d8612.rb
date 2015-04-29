class Hamming
  def self.compute(first, second)
    primary = first.length > second.length ? second.split('') : first.split('')
    secondary = first.length > second.length ? first.split('') : second.split('') 
    diff = 0

    primary.each_with_index do |protein, index|
      if protein != secondary[index]
        diff = diff + 1
      end
    end
    diff
  end
end
