class Phrase < Struct.new(:phrase)
  def word_count
    phrase.downcase.gsub(",", " ").gsub(/[:#&@%^!$]/, "").split(" ").each_with_object({}) do |word, result|
      result[word] ||= 0
      result[word] += 1
    end
  end
end
