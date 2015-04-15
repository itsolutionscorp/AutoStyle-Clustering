class DNA < String
  def to_rna
    each_char.map do |c|
      case
      when c == 'U' then 'T'
      when c == 'T' then 'U'
      else c
      end
    end.join
  end
end
