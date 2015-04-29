class ETL

  def self.transform(h)
    hash = Hash.new
    h.each_pair do |key, value|
      value.each do |v|
        hash[v.downcase] = key
      end
    end
    hash
  end

end
