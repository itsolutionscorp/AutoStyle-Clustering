module PigLatin
  extend self

  def translate(phrase)
    phrase.split.map { |word| transform word }.join " "
  end

private

  def transform(word)
    prefix = prefix(word)
    "#{word.sub(prefix, '')}#{prefix}ay"
  end

  def prefix(word)
    long_prefix(word) || ignored_prefix(word) || word[0]
  end

  def long_prefix(word)
    word.find_prefix *%w{ ch squ qu thr th sch }
  end

  def ignored_prefix(word)
    "" if word.find_prefix *%w{ a e i o u yt xr }
  end
end

class String
  def find_prefix(*prefixes)
    prefixes.find { |prefix| start_with? prefix }
  end
end
