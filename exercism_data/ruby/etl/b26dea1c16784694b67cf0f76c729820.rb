# Hereby assuming no data conflicts
# That is, A only has one value.

class ETL
  def self.transform legacy_data
    new_db = DB.new
    legacy_data.each_with_object(new_db) do |(value, keys), db|
      new_set = converter.call value: value, keys: keys
      db.merge! new_set
    end
    new_db.data_store
  end

  def self.converter
    @converter ||= KeyValueConverter.new
  end
end

class KeyValueConverter
  # Ruby 2.1 is bangin'
  def call(value:, keys:)
    keys.each_with_object(DB.new) do |key, db|
      db.add normalize_new_key(key), value
    end
  end

  private
  def normalize_new_key key
    key.downcase
  end
end

class DB
  attr_reader :data_store
  def add key, value
    data_store[key] = value
  end

  def data_store
    @db ||= {}
  end

  def merge! other_db
    data_store.merge! other_db.data_store
  end

end
