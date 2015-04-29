class PigLatin
  def self.translate(words)
    words.gsub(/\w+/) do |word|
      word.gsub(/([xy][^aeiou]\w*|[^aeiou]*q?u?(?=[aeiou]))(\w*)/, '\2\1ay')
    end
  end
end
