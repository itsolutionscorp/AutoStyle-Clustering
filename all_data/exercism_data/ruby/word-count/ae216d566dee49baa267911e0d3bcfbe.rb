class Phrase < Struct.new(:str)
  BOUNDRY = /[^a-zA-Z0-9']+/

  def word_count
    str.downcase.split(BOUNDRY).reduce(Hash.new(0)) { |h,w| h[w] += 1 ; h }
  end
end
