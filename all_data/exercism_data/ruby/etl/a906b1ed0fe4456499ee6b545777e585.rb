class ETL
  def self.transform(old)
    old.inject({}) do |new_map, (key, letters)|
      letters.each do|letter|
        new_map[letter.downcase] = key
      end
      new_map
    end
  end
end


# class ETL
#   def self.transform old
#     old.map do |k, v|
#       v.map do |x|
#         { x.downcase => k }
#       end
#     end.reduce(&:concat).reduce(&:merge)
#   end
# end

# class ETL
#   class << self
#     def transform(old)
#       old.each_with_object({}) do |(score, letters), result|
#         letters.each do |letter|
#           result[letter.downcase] = score
#         end
#       end
#     end
#   end
# end
