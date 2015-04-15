class ETL
  def self.transform(old_format)
    old_format.each_with_object({}) do |(points, characters), new_format|
      characters.each { |character| new_format[character.downcase] = points }
    end
  end
end
