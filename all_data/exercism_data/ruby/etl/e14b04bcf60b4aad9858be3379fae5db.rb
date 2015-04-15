module ETL
  # straight forward
  def self.transform(old)
    hash = {}
    old.each do |k,v|
      v.each do |element|
        hash[element.downcase] = k
      end
    end
    hash
  end

  # reduce no outer scope
  def self.transform(old)
    old.reduce({}) do |hash,elements|
      k, v = elements
      v.each do |element|
        hash[element.downcase] = k
      end
      hash
    end
  end

end
