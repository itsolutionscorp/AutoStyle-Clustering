module ETL
  def self.transform(origin)
    origin.inject({}) do |hash, (num, array)|
      array.each do |key|
        hash[key.downcase] = num
      end
      hash
    end
  end
end
