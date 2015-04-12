class Phrase
  def initialize msg
    @msg = msg.to_s
  end

  def word_count
    to_hash @msg
  end

  private

  def to_hash msg
    hash = Hash.new
    msg = sanitize msg # remove specail characters
    msg = msg.split(/,|\ /).reject(&:empty?) # split string to array
    msg.uniq.each do |item| 
      hash[item] = msg.count(item)
    end
    hash
  end

  def sanitize msg
    msg.delete("^a-zA-Z0-9 ,").downcase
  end
end
