#!/usr/bin/env ruby


class ETL
    def self.transform(old_data)
        new_data = {}
        old_data.keys.each do |value|
            old_data[value].each do |letter|
                new_data[letter.downcase] = value
            end
        end
        new_data
    end
end
