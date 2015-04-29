class Phrase

  def initialize sentence
    @sentence = sentence
  end

  def word_count
    words_frequency = Hash.new {|h, k| h[k] = 0}
    split(clean_sentence @sentence).reduce words_frequency do |wf, w|
      wf[w] += 1
      wf
    end
  end

  private

  def clean_sentence sentence
    sentence.gsub(/[^\w\s,']+/, '').downcase
  end

  def split sentence
    sentence.gsub(/,/, ' ').split ' '
  end
end
