class Atbash
  ABC = [*'a'..'z'].join
  R_ABC = ABC.reverse

  def self.encode(input)
    input.downcase.tr(ABC, R_ABC).gsub(/\W/, '').scan(/.{1,5}/).join(' ')
  end
end
