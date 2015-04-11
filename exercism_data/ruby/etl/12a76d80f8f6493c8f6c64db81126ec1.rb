class ETL
  def self.transform(old)
    new_version = {}

    old.each do |score, chars|
      chars.map!(&:downcase)
      chars.each { |char| new_version[char] = score }
    end

    new_version
  end
end
