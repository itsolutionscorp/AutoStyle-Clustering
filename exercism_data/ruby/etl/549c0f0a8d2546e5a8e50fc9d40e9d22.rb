class ETL
  def self.transform(alt)
    dict = {}
    alt.each do |k , v| 
      v.each do |let| 
        dict[let.downcase] = k
      end
    end
    dict
  end
end
