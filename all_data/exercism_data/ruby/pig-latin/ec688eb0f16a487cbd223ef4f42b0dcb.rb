module PigLatin
  extend self

  def translate(phrase)
    phrase.split.map { |word| transform word }.join " "
  end

private

  def transform(word)
    prefix = prefix(word)
    "#{word[prefix.length..-1]}#{prefix}ay"
  end

  def prefix(word)
    long_prefix(word) || ignored_prefix(word) || word[0]
  end

  def long_prefix(word)
    ["ch", "squ", "qu", "thr", "th", "sch"].find { |prefix| word.start_with? prefix }
  end

  def ignored_prefix(word)
    "" if has_prefix(word, "a", "e", "yt", "xr")
  end

  def has_prefix(word, *prefixes)
    prefixes.find { |prefix| word.start_with? prefix }
  end
end
