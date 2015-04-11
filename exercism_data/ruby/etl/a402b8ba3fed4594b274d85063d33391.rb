class ETL
  def self.transform(old)

    old.each_with_object({}) do |(score, chars), new_version|
      chars.each { |char| new_version[char.downcase] = score }
    end
  end
end
