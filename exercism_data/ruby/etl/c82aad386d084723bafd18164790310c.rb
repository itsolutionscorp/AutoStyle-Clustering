class ETL
  def ETL.transform(old)
    new = {}
    old.each do |key, value|
      value.each do | letter |
        new[letter.downcase] = key
      end 
    end 
 #   puts new
    return new
  end
end
  
