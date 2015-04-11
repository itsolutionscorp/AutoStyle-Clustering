module ETL
  def self.transform(data)
    new_dictionary = {}

    data.each do |pair|
      pair.last.each do |x|
        new_dictionary.merge!(x.downcase => pair.first)
      end
    end

    new_dictionary
  end
end
