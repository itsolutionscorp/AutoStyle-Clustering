class ETL
  
  class << self
    
    def transform(old)
      @new = Hash.new
      convert(old)
      return @new
    end

    def convert(old)
      old.each do |key, values|
        values.each do |value|
          @new[value.downcase] = key
        end
      end
    end  
  
  end

end
