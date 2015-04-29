# vim:fileencoding=utf-8


def accumulate(collection, func):
    return list(accumulate_generate(collection, func))


def accumulate_generate(collection, func):
    return (
        func(member) for member in collection
    )
