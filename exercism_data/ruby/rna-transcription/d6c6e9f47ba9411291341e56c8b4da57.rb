class Complement

  def of_dna(data)
    # rna_complement = []
    # split_data = data.split(//)
    # split_data.each do |letter|
    #   if letter == "G"
    #     rna_complement << "C"
    #   elsif letter == "C"
    #     rna_complement << "G"
    #   elsif letter == "T"
    #     rna_complement << "A"
    #   elsif letter == "A"
    #     rna_complement << "U"
    #   end
    # end
    # rna_complement.join
    data.gsub(/./,"G" => "C", "C"=>"G", "T"=> "A", "A"=>"U")
  end

  def of_rna(data)
    data.gsub(/./, "C"=>"G", "G"=>"C", "A"=>"T", "U"=>"A")
  end
end
