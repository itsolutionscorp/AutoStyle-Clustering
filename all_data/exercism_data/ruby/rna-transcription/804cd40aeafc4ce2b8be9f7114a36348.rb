class Complement
  @h = {"G" => "C","C" => "G","T"=>"A","A"=>"U"}

  def self.of_dna(dataset)
    d = dataset.chars
    d.map! { |key| @h[key]}
    d.join
  end

  def self.of_rna(dataset)
    d = dataset.chars
    d.map! {|value| @h.key(value)}
    d.join
  end
end
