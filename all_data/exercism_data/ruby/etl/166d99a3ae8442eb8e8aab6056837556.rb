class ETL
  def self.transform(oldhash)
    e = Hash.new()
    oldhash.each do |k,v|
      v.each do |l|
        e[l.downcase] = k
      end    
    end
    return e
  end
end
