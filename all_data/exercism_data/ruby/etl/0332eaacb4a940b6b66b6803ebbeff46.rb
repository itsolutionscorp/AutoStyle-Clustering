class ETL
  class << self

    def transform(old_format)
      new_format = {}
      
      old_format.each do | point, letters |
        # new_format << point.to_s
        letters.each do |letter|
          new_format[letter.downcase] = point
        end
        
      end
      return new_format
    end

  end
end
