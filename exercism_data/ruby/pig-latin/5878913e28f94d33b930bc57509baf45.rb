module PigLatin
  def self.translate(str)
    str.split(' ').map do |word|
      translate_word(word)
    end.join(' ')
  end

  def self.translate_word(str)
    if SPECIAL_STARTS.one? { |start| str.start_with?(start) }
      str + 'ay'
    else
      str.gsub(/^(.{1,2}hr?|.?qu|.)(.+)/, '\2\1ay')
    end
  end

  private

  SPECIAL_STARTS = %w(a e i o u xr yt)
end
