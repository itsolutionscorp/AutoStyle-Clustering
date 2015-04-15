class OCR

  OCR_HASH = {
    " _ \n| |\n|_|\n   " => "0",
    "   \n  |\n  |\n   " => "1",
    " _ \n _|\n|_ \n   " => "2",
    " _ \n _|\n _|\n   " => "3",
    "   \n|_|\n  |\n   " => "4",
    " _ \n|_ \n _|\n   " => "5",
    " _ \n|_ \n|_|\n   " => "6",
    " _ \n  |\n  |\n   " => "7",
    " _ \n|_|\n|_|\n   " => "8",
    " _ \n|_|\n _|\n   " => "9"
  }

  def initialize(text)
    @text = text
  end

  def make_num_groups
    num_groups = []
    @text.split("\n").each_slice(4) do |line|
      num_groups << line
    end
    num_groups
  end

  def ocr_conversion(group)
    character_chunk = group.map do |row|
      row.scan(/.{1,3}/) 
    end
    transposed_number = character_chunk.transpose.map do |number|
      number.join("\n")
    end
    transposed_number.map do |number|
      if OCR_HASH[number]
        OCR_HASH[number]
      else 
        "?"
      end
    end.join
  end

  def convert
    final_output = []
    make_num_groups.map do |group|
      final_output << ocr_conversion(group)
    end
    final_output.join(",")
  end

end
