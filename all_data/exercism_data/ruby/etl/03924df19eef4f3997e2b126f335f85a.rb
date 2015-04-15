module ETL
    def self.transform(old_hash)
        new_hash = {}

        old_hash.keys.each do |point_value|
            letter_list = old_hash[point_value]
            letter_list.each do |letter|
                new_hash[letter.downcase] = point_value
            end
        end
        
        new_hash
    end
end
