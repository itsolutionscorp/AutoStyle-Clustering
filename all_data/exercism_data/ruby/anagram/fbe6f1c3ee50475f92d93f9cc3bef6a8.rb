class Anagram

  def initialize(input_word)
    @original_word = input_word
    @sorted_word = @original_word.downcase.chars.sort.join
  end

  def create_sorted_hash(test_list)
    sorted_test_list = test_list.map { |test_w| test_w.downcase.chars.sort.join }
    sorted_hash = Hash[test_list.zip(sorted_test_list)]
  end

  def match(test_list)
    results = []
    temp_values = []
    processed_hash = create_sorted_hash(test_list)

    processed_hash.each do |k,v|
      next if k.downcase == @original_word
      next if temp_values.include? v
        temp_values << v
      if v == @sorted_word
        results << k unless results.include?(v)
      end
    end
    results
  end

end
