class Hamming

  def compute (base, mutation)
    base = base.split(//)
    mutation = mutation.split(//)
    hamming = 0
    base.each_with_index do |acid, index|
      if acid != mutation[index] and !mutation[index].nil?
        hamming += 1
      end
    end
    return hamming
  end

end
