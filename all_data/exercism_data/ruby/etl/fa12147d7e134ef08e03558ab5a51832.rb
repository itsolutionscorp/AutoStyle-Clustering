#ETL function
#for each value-letters combination in the old data, we take each
#letter, and add it the the new_hash with the corresponding
#value 
#
#
#Completed in 8.5 minutes
class ETL
  def self.transform(old)
    new_hash = {}
    old.each do |value,letters|
      letters.each do |letter|
        new_hash[letter.downcase] = value
      end
    end
    new_hash
  end
end
