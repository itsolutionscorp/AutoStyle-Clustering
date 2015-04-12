class Phrase
  def initialize msg
    @msg = msg.to_s
  end

  def word_count
    to_hash sanitize @msg
  end

  private

  def to_hash msg
    hash = Hash.new(0)
    msg = msg.split(/,|\ /).reject(&:empty?) # split string to array
    msg.each do |item| 
      hash[item] += 1
    end
    hash
  end

  def sanitize msg
    #Remove special characters
    msg.delete("^a-zA-Z0-9 ,").downcase
  end
end
