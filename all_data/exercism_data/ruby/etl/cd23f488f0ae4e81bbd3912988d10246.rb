module ETL
    def self.transform(old_hash)
        old_hash.keys.each_with_object({}) do |point_value, new_hash|
            letter_list = old_hash[point_value]
            letter_list.each do |letter|
                new_hash[letter.downcase] = point_value
            end
        end
    end
end
