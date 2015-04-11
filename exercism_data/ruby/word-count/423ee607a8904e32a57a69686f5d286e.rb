class Phrase < String

  def word_count
    replace tr('^A-Za-z0-9 ', '')
    split(' ').map(&:downcase).inject(Hash.new(0)) do |h,s|
      h[s] += 1
      h
    end
  end

end
