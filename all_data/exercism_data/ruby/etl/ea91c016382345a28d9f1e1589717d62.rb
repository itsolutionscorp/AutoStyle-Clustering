module ETL
  def self.transform(old)
    shiny_new = Hash.new { |h,k| h[k] = [] }
    old.each_pair { |value, letters|
      letters.each { |letter|
        shiny_new[letter.downcase] = value
      }
    }
    shiny_new
  end
end
