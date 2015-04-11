class ETL

  def self.transform(old)    
    old.each_with_object({}) do |(k, v), n|
      v.each { |l| n[l.downcase] = k }
    end
  end

end
