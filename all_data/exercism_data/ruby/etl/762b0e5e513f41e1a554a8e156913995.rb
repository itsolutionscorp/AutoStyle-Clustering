class ETL

  def self.transform old
    new = {}
    
    old.each do |k,v|
      v.each {|x| new[x.downcase] = k}
    end
    return new
  end


end
